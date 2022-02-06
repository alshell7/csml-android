/*
 * MIT License
 *
 * Copyright (c) 2022 alshell7
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.alshell.csmlsdk;

import android.util.Log;

import androidx.annotation.NonNull;

import com.alshell.csmlsdk.api.CSMLRequest;
import com.alshell.csmlsdk.api.CSMLResponse;
import com.alshell.csmlsdk.api.Client;
import com.alshell.csmlsdk.api.Message;
import com.alshell.csmlsdk.components.SendingPayload;
import com.alshell.csmlsdk.components.advanced.FlowTrigger;
import com.alshell.csmlsdk.components.standard.Audio;
import com.alshell.csmlsdk.components.standard.File;
import com.alshell.csmlsdk.components.standard.Image;
import com.alshell.csmlsdk.components.standard.Text;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Core CSML Engine. Responsible for setting up bot responses and ability to send the messages on behalf of a client/user.
 */
public class CSMLEngine {

    /**
     * CSML Studio API Endpoint. Referred from: https://docs.csml.dev/studio/api/introduction#endpoints
     */
    public static final String API_ENDPOINT_CSML_STUDIO = "https://clients.csml.dev/v1/api/";

    private static final String TAG = "CSMLEngine";
    private final ArrayList<BotResponseListener> botResponseListeners;


    private CSMLService apiService;
    private final String publicApiKey;
    private final String currentApiEndpoint;
    private final Client commonClient;
    private Map<String, String> commonMetaData = new HashMap<String, String>();

    private CSMLService getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(this.currentApiEndpoint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(CSMLService.class);
        }
        return apiService;
    }

    /**
     * Initialise the CSML Client for your bot.
     *
     * @param botPublicApiKey            Check: https://docs.csml.dev/studio/api/authentication#public-endpoints-authentication
     * @param userId                     A random client-issued string for tracing requests. If none is provided, will be automatically generated.
     * @param defaultBotResponseListener Event trigger received on processing the API request
     */
    public CSMLEngine(String botPublicApiKey, String userId, BotResponseListener defaultBotResponseListener) {
        this.publicApiKey = botPublicApiKey;
        this.currentApiEndpoint = API_ENDPOINT_CSML_STUDIO;
        this.commonClient = new Client(userId);
        this.botResponseListeners = new ArrayList<>();
        this.botResponseListeners.add(defaultBotResponseListener);
    }


    /**
     * Initialise the CSML Client for your bot hosted using CSML Open-Source
     *
     * @param apiEndpoint                If your CSML Bot is deployed using self hosted CSML Open-Source, you can provide the API endpoint to send the request to. (Currently not supported)
     * @param botPublicApiKey            Check: https://docs.csml.dev/studio/api/authentication#public-endpoints-authentication
     * @param userId                     A random client-issued string for tracing requests. If none is provided, will be automatically generated.
     * @param defaultBotResponseListener Event trigger received on processing the API request
     */
    public CSMLEngine(String apiEndpoint, String botPublicApiKey, String userId, BotResponseListener defaultBotResponseListener) {
        this.publicApiKey = botPublicApiKey;
        this.currentApiEndpoint = apiEndpoint;
        this.commonClient = new Client(userId);
        this.botResponseListeners = new ArrayList<>();
        this.botResponseListeners.add(defaultBotResponseListener);
    }

    /**
     * Metadata to be used for the client for all the messages.
     *
     * @param commonMetaData A pair of key values for the meta data
     */
    public void setCommonMetaData(Map<String, String> commonMetaData) {
        this.commonMetaData = commonMetaData;
    }

    /**
     * Send the message type-free to CSML API
     *
     * @param payload An Abstract payload to be sent to the bot.
     */
    private void sendMessage(SendingPayload payload) {
        getApiService().chat(this.publicApiKey, new CSMLRequest(this.commonClient, commonMetaData, payload)).enqueue(new Callback<CSMLResponse>() {
            @Override
            public void onResponse(@NonNull Call<CSMLResponse> call, @NonNull Response<CSMLResponse> response) {
                Log.d(TAG, "Message:" + response.raw().toString());
                Log.d(TAG, "Status Code:" + String.valueOf(response.code()));
                CSMLResponse botResponse = response.body();
                if (botResponse != null) {
                    Log.d(TAG, "Successfully parsed");
                    Log.d(TAG, "Received bot by Request Id: " + botResponse.REQUEST_ID);
                    for (BotResponseListener listener : botResponseListeners) {
                        for (Message message : botResponse.MESSAGES) {
                            if (message != null) {
                                Log.d(TAG, "Message: Conversation ID :" + message.CONVERSATION_ID);
                                Log.d(TAG, "Message: Content Type :" + message.getContentType());
                                switch (message.getContentType()) {
                                    case text:
                                        listener.onTextReceived(message.toText());
                                        Log.d(TAG, "Message: Content :" + message.getContent().text);
                                        break;
                                    case image:
                                        listener.onImageReceived(message.toImage());
                                        Log.d(TAG, "Message: Content :" + message.getContent().url);
                                        break;
                                    case audio:
                                        listener.onAudioReceived(message.toAudio());
                                        Log.d(TAG, "Message: Content :" + message.getContent().url);
                                        break;
                                    case video:
                                        listener.onVideoReceived(message.toVideo());
                                        Log.d(TAG, "Message: Content :" + message.getContent().url);
                                        break;
                                    case file:
                                        listener.onFileReceived(message.toFile());
                                        Log.d(TAG, "Message: Content :" + message.getContent().url);
                                        break;
                                    case url:
                                        listener.onUrlReceived(message.toUrl());
                                        Log.d(TAG, "Message: Content :" + message.getContent().url);
                                        break;
                                    case button:
                                        listener.onButtonReceived(message.toButton());
                                        Log.d(TAG, "Message: Content :" + message.getContent().title);
                                        break;
                                    case question:
                                        listener.onQuestionReceived(message.toQuestion());
                                        Log.d(TAG, "Message: Content :" + message.getContent().title);
                                        break;
                                    case typing:
                                        listener.onTypingReceived(message.toTyping());
                                        Log.d(TAG, "Message: Content :" + String.valueOf(message.getContent().duration));
                                        break;
                                    case wait:
                                        listener.onWaitReceived(message.toWait());
                                        Log.d(TAG, "Message: Content :" + String.valueOf(message.getContent().duration));
                                        break;
                                    case debug:
                                        listener.onDebugReceived(message.toDebug());
                                        break;
                                    case error:
                                        listener.onErrorReceived(message.toError());
                                        Log.d(TAG, "Message: Content :" + String.valueOf(message.getContent().error));
                                        break;
                                    default:
                                        listener.onUnIdentifiableReceived(message);
                                        break;
                                }
                            }

                        }
                    }
                } else {
                    Log.e(TAG, "The CSML response was null.");
                    throwInternalBotException(new Throwable("The CSML response was null. API Status Code: " + String.valueOf(response.code())));
                }
            }

            @Override
            public void onFailure(@NonNull Call<CSMLResponse> call, @NonNull Throwable reason) {
                Log.e(TAG, "CSML API call failed", reason);
                throwInternalBotException(reason);
            }
        });
    }

    /**
     * To make sure the URL provided is a valid URL. A web link and not a local file URI.
     *
     * @param urlString URL to validate
     * @return True if the URL was valid, else false.
     */
    private boolean validateUrl(String urlString) {
        try {
            new URL(urlString).toURI();
            return true;
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * An internal bot exception thrower.
     *
     * @param reason A throwable exception specifying the issue while sending the message.
     */
    private void throwInternalBotException(Throwable reason) {
        for (BotResponseListener listener : botResponseListeners) {
            listener.onMessageSendFailed(reason);
        }
    }

    /**
     * Send a simple text message to the bot.
     *
     * @param message A text message to be sent.
     */
    public void sendText(String message) {
        sendMessage(new Text(message));
    }

    /**
     * Send an image message to the bot.
     *
     * @param imageUrl A web-url, hosted URL of the image asset.
     */
    public void sendImage(String imageUrl) {
        if (validateUrl(imageUrl)) {
            sendMessage(new Image(imageUrl));
        } else {
            throwInternalBotException(new Throwable("The image Url is not under a valid URL"));
        }
    }

    /**
     * Send an audio message to the bot.
     *
     * @param audioURL A web-url, hosted URL of the image asset.
     */
    public void sendAudio(String audioURL) {
        if (validateUrl(audioURL)) {
            sendMessage(new Audio(audioURL));
        } else {
            throwInternalBotException(new Throwable("The audio Url is not under a valid URL"));
        }
    }

    /**
     * Send a video message to the bot.
     *
     * @param videoURL A web-url, hosted URL of the video asset.
     */
    public void sendVideo(String videoURL) {
        if (validateUrl(videoURL)) {
            sendMessage(new Audio(videoURL));
        } else {
            throwInternalBotException(new Throwable("The video Url is not under a valid URL."));
        }
    }

    /**
     * Send a file message to the bot.
     *
     * @param fileURL A web-url, hosted URL of the file asset.
     */
    public void sendFile(String fileURL) {
        if (validateUrl(fileURL)) {
            sendMessage(new File(fileURL));
        } else {
            throwInternalBotException(new Throwable("The file Url is not under a valid URL."));
        }
    }

    /**
     * Send a URL message to the bot.
     *
     * @param rawURL A web-url.
     */
    public void sendURL(String rawURL) {
        if (validateUrl(rawURL)) {
            sendMessage(new com.alshell.csmlsdk.components.standard.URL(rawURL));
        } else {
            throwInternalBotException(new Throwable("The raw Url is not under a valid URL."));
        }
    }

    /**
     * Send a URL message with title and text to the bot.
     *
     * @param rawURL A web-url.
     * @param title  Title of the URL.
     * @param text   Text message with the URL.
     */
    public void sendURL(String rawURL, String title, String text) {
        if (validateUrl(rawURL)) {
            sendMessage(new com.alshell.csmlsdk.components.standard.URL(rawURL, title, text));
        } else {
            throwInternalBotException(new Throwable("The raw Url is not under a valid URL."));
        }
    }

    /**
     * Send a specific flow trigger to the bot at any point of conversation.
     *
     * @param flowID     The CSML Flow ID of the Bot.
     * @param closeFlows Whether to close the active flows.
     */
    public void sendFlowTrigger(String flowID, boolean closeFlows) {
        sendMessage(new FlowTrigger(flowID, closeFlows));
    }

    /**
     * Add an additional listener to the bot events.
     *
     * @param botResponseListener Listener to be added.
     */
    public void addChatResponseListener(BotResponseListener botResponseListener) {
        this.botResponseListeners.add(botResponseListener);
    }
}
