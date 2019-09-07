# frozen_string_literal: true

require 'jwt'

class AuthenticateController < ApplicationController
  # POST at /authenticate [returns jwt if credentials in params are valid]
  def create
    result = get_result(params)

    json_response(result[:user], result[:status])
  end

  # GET at /authenticate [returns public key]
  def index
    json_response({public_key: ENV['PUBLIC_KEY']}, 200)
  end

  private

  def get_result(params)
    login = params[:username]
    # password = params[:password]
    response = authenticate(login)

    handle_response_status(response)
  end

  def handle_response_status(response)
    status = response[:status]
    username = response[:username]
    user = {id: username, username: username, token: generate_jwt_token(username)}

    return {status: :ok, user: user} if status == 200

    {status: :unauthorized}
  end

  def generate_jwt_token(username)
    JWT.encode get_payload(username),
               OpenSSL::PKey::RSA.new(Base64.decode64(ENV['PRIVATE_KEY'])),
               'RS256'
  end

  def get_payload(username)
    exp = Time.now.to_i + 4 * 3600

    {
        username: username,
        role: 'user role to be checked in a db to see access level',
        exp: exp,
        iss: 'auth microservice'
    }
  end

  def authenticate(login)
    if %w(white-rabbit-guest black-rabbit-guest green-rabbit-guest blue-rabbit-guest red-rabbit-guest rabbit-admin the-unstable-one).include? login
      {
          status: 200,
          username: login
      }
    else
      {
          status: :unauthorized,
          username: login
      }
    end
  end
end
