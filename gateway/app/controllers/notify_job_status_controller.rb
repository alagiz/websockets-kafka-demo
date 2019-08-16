# frozen_string_literal: true

class NotifyJobStatusController < ApplicationController
  # POST at /authenticate - posts credentials to the authentication service.
  # [returns jwt in the return message if successful]
  def create
    puts "params"
    job = params[:notify_job_status]

    ActionCable.server.broadcast(
        "web_notifications_channel_#{job[:userId]}",
        job: job,
        time_stamp: Time.now.to_s
    )

    if job[:userId] != "rabbit-admin"
      ActionCable.server.broadcast(
          "web_notifications_channel_rabbit-admin",
          job: job,
          time_stamp: Time.now.to_s
      )
    end

    :ok
  end
end
