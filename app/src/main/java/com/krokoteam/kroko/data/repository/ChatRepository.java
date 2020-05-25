package com.krokoteam.kroko.data.repository;

import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.krokoteam.kroko.data.model.ChatMessage;
import com.krokoteam.kroko.utils.Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChatRepository {
    private FirebaseFirestore mFirebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference mChatReferences;
    private MutableLiveData<List<ChatMessage>> mMessagesInChat = new MutableLiveData<>();
    private static final String CHAT_COLLECTION_TAG = "chat",
                                USER_ID_TAG = "mSenderID",
                                ROOM_ID_TAG = "mRoomID",
                                MESSAGE_TAG = "mMessage",
                                MESSAGES_TAG = "mMessages";

    private static final String LOG_TAG = ChatRepository.class.getSimpleName();

    private static ChatRepository mInstance;

    public static ChatRepository getInstance() {
        if (mInstance == null) {
            mInstance = new ChatRepository();
        }
        return mInstance;
    }

    private ChatRepository() {
        mChatReferences = mFirebaseFirestore.collection(CHAT_COLLECTION_TAG);
    }

    public void setupChatRepository(String roomID) {
        mChatReferences.whereEqualTo(ROOM_ID_TAG, roomID).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                assert queryDocumentSnapshots != null;
                for (DocumentChange chatChanges : queryDocumentSnapshots.getDocumentChanges()) {
                    QueryDocumentSnapshot chatInfo = chatChanges.getDocument();
                    List<HashMap<String, Object>> messages =
                            (List<HashMap<String, Object>>) chatInfo.get(MESSAGES_TAG);
                    List<ChatMessage> chat = new ArrayList<>();
                    for (HashMap<String, Object> message : messages) {
                        ChatMessage chatMessage = new ChatMessage(
                                message.get(USER_ID_TAG).toString(),
                                message.get(MESSAGE_TAG).toString()
                        );
                        chat.add(chatMessage);
                    }
                    mMessagesInChat.setValue(chat);
                }
            }
        });
    }

    public void createChannel(String channelID) {
        Map<String, Object> chats = new HashMap<>();
        chats.put(ROOM_ID_TAG, channelID);
        ArrayList<Map<String, Object>> messages = new ArrayList<>();

        Map<String, Object> message = new HashMap<>();
        message.put(USER_ID_TAG, 0);
        message.put(MESSAGE_TAG, "Добро пожаловать в игру крокодил!");
        messages.add(message);
        chats.put(MESSAGES_TAG, messages);

        mChatReferences.document(channelID).set(chats);
    }

    public LiveData<List<ChatMessage>> getChatMessagesLiveData() {
        return mMessagesInChat;
    }
}
