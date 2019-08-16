Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  match '*path', :controller => 'application', :action => 'handle_options_request', :via => :options
  resources :authenticate, only: %i[index create]
  resources :perform, only: %i[index create destroy]
  resources :notify_job_status, only: %i[create]
  get 'authenticate/stale'
end
