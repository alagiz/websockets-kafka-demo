class AllJobsChannel < ApplicationCable::Channel
  def subscribed
    stream_from "all_jobs_channel"
  end

  def receive(message)
    AllJobsRequestResponder.call({:userId =>  message['userId']}.to_json)
  end

  def unsubscribed
    # Any cleanup needed when channel is unsubscribed
  end
end
