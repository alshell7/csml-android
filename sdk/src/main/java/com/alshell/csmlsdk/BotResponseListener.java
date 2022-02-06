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

/**
 * Event trigger received on processing the API request
 */
public interface BotResponseListener {
    /**
     * Triggers when sending the message failed.
     *
     * @param reason A throwable on any exception happened while communicating with CSML Studio/Open-Source
     */
    public void onMessageSendFailed(Throwable reason);

    /**
     * Gets triggered when the bot responds a text message.
     *
     * @param text A Text Message
     */
    public void onTextReceived(Text text);

    /**
     * Gets triggered when the bot responds an Image message.
     *
     * @param image Am Image Message
     */
    public void onImageReceived(Image image);

    /**
     * Gets triggered when the bot responds an Audio message.
     *
     * @param audio Am Audio Message
     */
    public void onAudioReceived(Audio audio);

    /**
     * Gets triggered when the bot responds a Video message.
     *
     * @param video A Video Message
     */
    public void onVideoReceived(Video video);

    /**
     * Gets triggered when the bot responds an URL message.
     *
     * @param url A URL Message
     */
    public void onUrlReceived(URL url);

    /**
     * Gets triggered when the bot responds a File message.
     *
     * @param file A File Message
     */
    public void onFileReceived(File file);

    /**
     * Gets triggered when the bot responds a Button message.
     *
     * @param button A Button Message
     */
    public void onButtonReceived(Button button);

    /**
     * Gets triggered when the bot responds a Question message.
     *
     * @param question A Question Message
     */
    public void onQuestionReceived(Question question);

    /**
     * Gets triggered when the bot responds a Typing simulation message.
     *
     * @param typing A Typing Message
     */
    public void onTypingReceived(Typing typing);

    /**
     * Gets triggered when the bot responds a Wait simulation message.
     *
     * @param wait A Wait Message
     */
    public void onWaitReceived(Wait wait);

    /**
     * Gets triggered when the CSML bot flow prints a debugging message.
     *
     * @param debug A Debug Message
     */
    public void onDebugReceived(Debug debug);

    /**
     * Gets triggered when the CSML bot flow encounters.
     *
     * @param error An Error Message
     */
    public void onErrorReceived(Error error);

    /**
     * Gets triggered when an unidentifiable/custom payload message is received from the CSML flow.
     *
     * @param message An Abstract CSML-Android Message
     */
    public void onUnIdentifiableReceived(Message message);
}
