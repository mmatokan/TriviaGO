package hr.fer.ruazosa.networkquiz;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.SendResponse;
import com.google.firebase.messaging.TopicManagementResponse;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushFcmOptions;
import com.google.firebase.messaging.WebpushNotification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseMessages {

    public void sendMulticastMessages(Integer gameId, List<String> tokens) throws FirebaseMessagingException {

        MulticastMessage message = MulticastMessage.builder().putData("game", gameId.toString()).addAllTokens(tokens).build();
        BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);

        if (response.getFailureCount() > 0){
            List<SendResponse> responses = response.getResponses();
            List<String> failedTokens = new ArrayList<>();
            for (int i = 0; i < responses.size(); i++) {
                if (!responses.get(i).isSuccessful()) {
                    // The order of responses corresponds to the order of the registration tokens.
                    failedTokens.add(tokens.get(i));
                }
            }
            System.out.println("List of tokens that caused failures: " + failedTokens);



        }
    }
}
