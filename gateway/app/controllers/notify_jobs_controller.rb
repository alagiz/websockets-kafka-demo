# frozen_string_literal: true

class NotifyJobsController < ApplicationController
  def create
    job = params[:notify_job]

    ActionCable.server.broadcast(
        "all_jobs_channel",
        jobList: job[:jobList],
        time_stamp: Time.now.to_s
    )

    if job[:userId] != "rabbit-admin"
      ActionCable.server.broadcast(
          "all_jobs_channel",
          jobList: job[:jobList],
          time_stamp: Time.now.to_s
      )
    end

    :ok
  end
end
