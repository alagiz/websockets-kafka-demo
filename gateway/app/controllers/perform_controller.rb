# frozen_string_literal: true

class PerformController < ApplicationController
  def create
    JobsResponder.call(params[:perform].to_json)

    :ok
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
