# frozen_string_literal: true

class JobRequestResponder < ApplicationResponder
  topic 'queueing.job.request'

  def respond(event_payload)
    respond_to 'queueing.job.request', event_payload
  end
end
