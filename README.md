Xatkit Google Assistant Platform
=====

[![License Badge](https://img.shields.io/badge/license-EPL%202.0-brightgreen.svg)](https://opensource.org/licenses/EPL-2.0)
[![Build Status](https://travis-ci.com/xatkit-bot-platform/xatkit-google-assistant-platform.svg?branch=master)](https://travis-ci.com/xatkit-bot-platform/xatkit-google-assistant-platform)
[![Wiki Badge](https://img.shields.io/badge/doc-wiki-blue)](https://github.com/xatkit-bot-platform/xatkit-google-assistant-platform/blob/master/examples/GoogleBasicReply/README.md)

Create a Google Assistant Action server and respond to the user using the Xatkit defined intent library. This platform is bundled with the [Xatkit release](https://github.com/xatkit-bot-platform/xatkit-releases/releases).

## Providers

| Provider                   | Type  | Context Parameters | Description                                                  |
| -------------------------- | ----- | ------------------ | ------------------------------------------------------------ |
| GoogleAssistantIntentProvider | Intent | - `googleassistant.request_id`: the unique identifier of the Google Assistant single request<br/> - `googleassistant.username`: the full name (*empty* if not available from permissions) of the Google user that sent the request<br/> - `googleassistant.user_id`: the Google unique identifier of the user that sent the request<br/> - `googleassistant.session_id`: the Google Assistant unique identifier of the ongoing and active conversation for the user<br/> - `<<sessionId>>`: A generated unique context containing all the replies per conversation. Used by the Reply action mechanism| Receive messages from Google Assistant requests and translates them into Xatkit-compatible intents.|


## Actions

| Action  | Parameters | Return                                  | Return Type | Description                                     |
| ------- | ---------- | --------------------------------------- | ----------- | ----------------------------------------------- |
| Reply | - `message` (**String**): the content of the SimpleText that has to be spoken by Google Assistant devices | `null` | Integer | Creates a session variable storing the reply message to be sent back to Google Assistant as a SimpleText |

## Options

The Google Assistant platform supports the following configuration options

| Key                  | Values | Description                                                  | Constraint    |
| -------------------- | ------ | ------------------------------------------------------------ | ------------- |
| `xatkit.google.assistant.responseNotFoundMessage` | String | The message that Xatkit uses to respond back to Google Assistant in the case something wrong happens during the processing of the request  | **Optional** |

## Usage

Check [our tutorial here](https://github.com/xatkit-bot-platform/xatkit-google-assistant-platform/tree/master/examples/GoogleBasicReply#xatkit---google-assistant-platform) to know how to **deploy a Google Assistant action** and connect it to **Xatkit** 
