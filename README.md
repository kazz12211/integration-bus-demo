# Integration Bus Demo

Minimum reference implementation of Integration Bus.

WorkflowManager (EventManager) listens WorkflowEvent and IntegrationEvent.

IntegrationBus is a WorkflowEventListener.

While workflow process, there occurs many workflow events. WorkflowManager's event listener catch those events and pass them to event listeners.

When external system post data to the app with REST API, the request handler post IntegrationEvent to EventManager (WorkflowManager in this case).

IntegrationBus registered as an event listener put those events in BusMessage then send them to Input/Output channels.

Input/Output channels are registered to IntegrationBus.

Input/Output channels have data transformer which transform data in BusMessage to app specific data type. So Input/Output channels get transformed data and can process the data.


Developer should/want to

- implements app specific channels and transformers
- register channels to IntegrationBus

If you can inject Input/Output channels, the app can be expanded easily.
