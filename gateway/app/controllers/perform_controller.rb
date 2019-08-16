# frozen_string_literal: true

class PerformController < ApplicationController
  # POST at /authenticate - posts credentials to the authentication service.
  # [returns jwt in the return message if successful]
  def create
    conn = Faraday.new(url: Settings.performer_service)

    response = conn.post do |req|
      req.url '/perform/job'
      req.headers['Content-Type'] = 'application/json'
      req.body = params.to_json
    end

    status = response.status
    user = response.body

    json_response(user, status)
  end

  def index
    conn = Faraday.new(url: Settings.performer_service)

    response = conn.get do |req|
      req.url '/perform/job'
      req.headers['Content-Type'] = 'application/json'
      req.params = params.except(:controller, :action)
    end

    status = response.status
    user = response.body

    json_response(user, status)
  end

  def destroy
    conn = Faraday.new(url: Settings.performer_service)

    response = conn.delete do |req|
      req.url '/perform/job'
      req.headers['Content-Type'] = 'application/json'
    end

    status = response.status
    user = response.body

    json_response(user, status)
  end
end
