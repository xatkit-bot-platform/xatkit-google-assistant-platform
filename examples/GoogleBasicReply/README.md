# Xatkit - Google Assistant Platform
Welcome to the Google Assistant Platform for Xatkit wiki!

This platform aims to integrate **Google Assistant voice recognition** inside a Xatkit application, using the intent library defined in the bot to let the flow of the conversation follow its path. Deploying a Google Assistant action will now be simple and fast and will be compatible with your previously developed Xatkit bots.

## Create your Xatkit bot with Google Assistant Platform

### Deploy the Google Assistant intent provider

Adding **Google Assistant intent provider** to your Xatkit applications is really simple. If you haven't already, we strongly suggest you to follow the [main tutorial](https://github.com/xatkit-bot-platform/xatkit-runtime/wiki) so that you will get a good overview on how to handle the various aspects of the framework.
> Note: This tutorial will use the `GoogleBasicReply` [example](https://github.com/xatkit-bot-platform/xatkit-google-assistant-platform/tree/master/examples/GoogleBasicReply) to illustrate the intent provider and Reply action usage.

In your `*.execution` file you will need to add the import for the `GoogleAssistantPlatform` with the consequent declaration of the usage of the `GoogleAssistantIntentProvider`, as shown below:

```xtext
import library "GoogleAssistantBasicReply/GoogleAssistantBasicReply-Lib.xmi" as GoogleAssistantBasicReplyLib
import library "CoreLibrary"
import platform "GoogleAssistantPlatform"

use provider GoogleAssistantPlatform.GoogleAssistantIntentProvider
```
This will create a _REST Endpoint_ in Xatkit runtime on `google/assistant/receiver/` when you will launch it in the terminal:
```bash
[INFO]  18:42:05,358 - Registering com.xatkit.alexa.platform.io.GoogleAssistantIntentProvider@1672d2c4 in the XatkitServer
[INFO]  18:42:05,358 - Starting RuntimeEventProvider GoogleAssistantIntentProvider
```
### Use ReplyAction

We currently have only one simple action you can perform to respond to Google Assistant requests, which takes as an argument a `message` string. You can use it in your execution file as follows:
```xtext
on intent Basic do 
	action GoogleAssistantPlatform.Reply(message: "Hi! How are you?")
```
Note that a Reply action should always be included inside an intent action covered by the Google Assistant dialog flow. Otherwise, since Google Assistant expects a result for each request that has been sent, the `xatkit.google.assistant.responseNotFoundMessage` defined in the `.properties` file will be use.

# Deploy your Google Assistant action
Now that you have a working and running Xatkit environment ready to talk with Google Assistant, you need to create a **Google Developer account** to start deploying Google Assistant actions. If you don't have one already, you can create it [HERE](https://developers.google.com/).

## Create a new Action

To create Actions, go to your [Google Actions Developer Console](https://console.actions.google.com/) and create a **New project**. Enter your new Action name and choose **Conversational** as development experience and later **Decide how your Action is invoked** to choose your invocation sentence.

Now go back to the **Overview** tab in the top bar and click on **Add Action(s)**. Choose **Custom intent** and click **BUILD**. You will be redirected to the *DialogFlow page* dedicated to your new Action. Click on **CREATE** to enable your new action in the Dialogflow console.

Navigate to the **Intents menu** on the sidebar and edit the *Default Fallback Intent*. Name it **GeneralIntent**, remove every *Response* and check every option in *Fulfillment*.

Do the same with the *Default Welcome Intent*: Name it **StartIntent**, remove *Events*,*Training Phrases* and *Responses*, and check everything under *Fulfillment*.

Now you have a working Google Assistant Action that handles every possible user input

## Add the Endpoint
To have an Action working you need a public REST Endpoint that works over HTTPS. If you have one already, you can add it to the **Fullfilmen** section of the console menu, under the **Webhook** choice, which must be activated.

Otherwise, you can follow [this tutorial](https://github.com/xatkit-bot-platform/xatkit-google-assistant-platform/tree/master/examples/GoogleBasicReply#deploy-ngrok) to know how to create your hosted endpoint using **Ngrok**

## Test your action
Head over to the **Integrations** section from the DialogFlow developer console **sidebar**. Choose **Google Assistant**, insert *StartIntent* as *Explicit invocation* and click on **Test**. Now you can start talking to Xatkit through this action, using the **Google Assistant simulator**!

-------------------------------

## Deploy Ngrok

If you're here, it's beacause you need a fast (and free) way to obtain a public reachable Endpoint for your deployed Google Assistant action that has to work with Xatkit. You can accomplish this task using the **Ngrok** service.
To use the HTTPS Tunneling you need, first go to [their website](https://ngrok.com/) and **Sign up**. Then, head to the [download area](https://ngrok.com/download) and follow their **4-steps instructions** until the last one, where you have to put port **5000** instead of 80 in the command line to have Xatkit server exposed publically:

```bash
./ngrok http 5000
```

You will notice two **Forwarding** voices on the console, like this:

```bash
Forwarding                    http://xxxxxxxx.ngrok.io -> http://localhost:5000
Forwarding                    https://xxxxxxxx.ngrok.io -> http://localhost:5000
```
Copy the **https** one (_https://xxxxxxxx.ngrok.io_), go to the **Endpoint** section of your skill in the Alexa developer console, select **HTTPS** mode and paste it in the region field you desire. Under that, select the option that says
```
My development endpoint is a sub-domain of a domain that has a wildcard certificate from a certificate authority
```
Save and Build the model. Your Google Assistant action is now ready to talk with Xatkit, [go out and test it](https://github.com/xatkit-bot-platform/xatkit-google-assistant-platform/tree/master/examples/GoogleBasicReply#test-your-skill)!
> Note: Ngrok service remains open until you close the terminal or restart your computer. There's need to restart it every time you restart your Xatkit bot, but every time you reload the service you need to change the webhook on your action.
