# frozen_string_literal: true

require 'jwt'

class ValidateJwtController < ApplicationController
  # GET at /validate_jwt [returns :ok if valid, :unauthorized otherwise]
  def index
    # TODO: expand to cover user role, etc.
    public_key = OpenSSL::PKey::RSA.new(Base64.decode64(ENV['PUBLIC_KEY']))
    auth_header = request.headers['Authorization']
    token = auth_header.split(' ').last

    JWT.decode token, public_key, true, algorithm: 'RS256'

    head :ok
  rescue StandardError => exception
    # TODO: expand on cases of expired token, wrong username etc.
    json_response({ error: exception.to_s  }, :unauthorized)
  end
end
