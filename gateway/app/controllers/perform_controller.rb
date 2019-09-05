# frozen_string_literal: true

class PerformController < ApplicationController
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
