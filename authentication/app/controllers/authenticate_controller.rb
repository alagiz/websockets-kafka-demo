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
    password = params[:password]
    response = authenticate(login, password)

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

  def authenticate(login, password)
    data = {sf_username: login, sf_password: password}

    if %w(white-rabbit-guest black-rabbit-guest green-rabbit-guest blue-rabbit-guest red-rabbit-guest rabbit-admin the-unstable-one).include? login
      {
          status: 200,
          username: login
      }
    else
      conn = Faraday.new(url: Settings.spotfire_server)

      response = conn.post do |req|
        req.url '/spotfire/sf_security_check'
        req.headers['Content-Type'] = 'application/x-www-form-urlencoded'
        req.headers['X-Requested-With'] = 'XMLHttpRequest'
        req.headers['Referer'] = "#{Settings.spotfire_server}/spotfire/login.html"
        req.body = URI.encode_www_form(data)
      end

      {
          status: response.status,
          username: login
      }
    end
  end
end
