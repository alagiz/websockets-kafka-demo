class JobStateNotificationsChannel < ApplicationCable::Channel
  def subscribed
    stream_from "job_state_notification_channel_#{params[:id]}"
  end

  def receive(message)
    JobRequestResponder.call(message.to_json)
  end

  def unsubscribed
    # Any cleanup needed when channel is unsubscribed
  end
end
