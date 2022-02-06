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

package com.alshell.csml;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alshell.csml.databinding.ActivityMainBinding;
import com.alshell.csmlsdk.BotResponseListener;
import com.alshell.csmlsdk.CSMLEngine;
import com.alshell.csmlsdk.api.Message;
import com.alshell.csmlsdk.components.advanced.Button;
import com.alshell.csmlsdk.components.advanced.Debug;
import com.alshell.csmlsdk.components.advanced.Question;
import com.alshell.csmlsdk.components.advanced.Typing;
import com.alshell.csmlsdk.components.advanced.Wait;
import com.alshell.csmlsdk.components.standard.Audio;
import com.alshell.csmlsdk.components.standard.File;
import com.alshell.csmlsdk.components.standard.Image;
import com.alshell.csmlsdk.components.standard.Text;
import com.alshell.csmlsdk.components.standard.URL;
import com.alshell.csmlsdk.components.standard.Video;
import com.fasilthottathil.simplechatview.model.ChatMessage;
import com.fasilthottathil.simplechatview.widget.SimpleChatView;

import java.util.UUID;

/**
 * A sample app to demonstrate CSML Engine Android SDK
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CSMLEngine csmlEngine;

    /**
     * API Channel - Key
     * Will be regenerating the API keys after sometime :P
     */
    private static final String BOT_PUBLIC_API_KEY = "eNTLKfNyvzKHnocaoWQazF4AfcTdDJY4";
    private static final String CUSTOM_USER_ID = "alshell7";

    private ActivityMainBinding binding;

    /*
     * View Types for Chat View
     *
     * */
    private static final int TYPE_TEXT_RIGHT = 0;
    private static final int TYPE_TEXT_LEFT = 1;
    private static final int TYPE_IMAGE_RIGHT = 2;
    private static final int TYPE_IMAGE_LEFT = 3;
    private static final int TYPE_VIDEO_RIGHT = 4;
    private static final int TYPE_VIDEO_LEFT = 5;

    private static final String BOT_PROFILE_PIC = "https://i.ibb.co/JvtLnVJ/csml.jpg";
    private static final String USR_PROFILE_PIC = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupCsmlUI();
        setupCsmlBackend();

    }


    /**
     * Setting up sending message on UI, just the text message for now.
     */
    private void setupCsmlUI() {
        binding.simpleChatView.setOnMessageSendListener(msg -> {
            ChatMessage message = new ChatMessage(
                    UUID.randomUUID().toString(),
                    msg,
                    CUSTOM_USER_ID,
                    USR_PROFILE_PIC,
                    true,
                    System.currentTimeMillis(),
                    SimpleChatView.TYPE_TEXT,
                    TYPE_TEXT_RIGHT
            );
            binding.simpleChatView.addMessage(message);
            csmlEngine.sendText(msg);
            return null;
        });
    }

    /**
     * Setting up the CSML Engine
     */
    private void setupCsmlBackend() {
        csmlEngine = new CSMLEngine(BOT_PUBLIC_API_KEY, CUSTOM_USER_ID, new BotResponseListener() {
            @Override
            public void onMessageSendFailed(Throwable reason) {
            }

            @Override
            public void onTextReceived(Text text) {
                runOnUiThread(() -> {
                    ChatMessage message = new ChatMessage(
                            UUID.randomUUID().toString(),
                            text.getTextMessage(),
                            "BOT",
                            BOT_PROFILE_PIC,
                            false,
                            System.currentTimeMillis(),
                            SimpleChatView.TYPE_TEXT,
                            TYPE_TEXT_LEFT
                    );
                    binding.simpleChatView.addMessage(message);
                });
            }

            @Override
            public void onImageReceived(Image image) {
                runOnUiThread(() -> {
                    ChatMessage message = new ChatMessage(
                            UUID.randomUUID().toString(),
                            image.getUrl(),
                            "BOT",
                            BOT_PROFILE_PIC,
                            false,
                            System.currentTimeMillis(),
                            SimpleChatView.TYPE_IMAGE,
                            TYPE_IMAGE_LEFT
                    );
                    binding.simpleChatView.addMessage(message);
                });

            }

            @Override
            public void onAudioReceived(Audio audio) {

            }

            @Override
            public void onVideoReceived(Video video) {

            }

            @Override
            public void onUrlReceived(URL url) {

            }

            @Override
            public void onFileReceived(File file) {

            }

            @Override
            public void onButtonReceived(Button button) {

            }

            @Override
            public void onQuestionReceived(Question question) {

            }

            @Override
            public void onTypingReceived(Typing typing) {

            }

            @Override
            public void onWaitReceived(Wait wait) {

            }

            @Override
            public void onDebugReceived(Debug debug) {

            }

            @Override
            public void onErrorReceived(Error error) {

            }

            @Override
            public void onUnIdentifiableReceived(Message message) {

            }
        });
    }

}