class ApplicationController < ActionController::API
  include Response

  def handle_options_request
    head(:ok) if request.request_method == "OPTIONS"
  end
end
