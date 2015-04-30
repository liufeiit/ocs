package com.notnoop.apns;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.notnoop.apns.ReconnectPolicy.Provided;
import com.notnoop.apns.internal.ReconnectPolicies;
import com.notnoop.exceptions.InvalidSSLConfig;

public class MainClass {

    public static void main(final String[] args) throws InvalidSSLConfig, FileNotFoundException {
        final ApnsDelegate delegate = new ApnsDelegate() {
            public void messageSent(final ApnsNotification message, final boolean resent) {
                System.out.println("Sent message " + message + " Resent: " + resent);
            }
            public void messageSendFailed(final ApnsNotification message, final Throwable e) {
                System.out.println("Failed message " + message);

            }
            public void connectionClosed(final DeliveryError e, final int messageIdentifier) {
                System.out.println("Closed connection: " + messageIdentifier + "\n   deliveryError " + e.toString());
            }

            public void cacheLengthExceeded(final int newCacheLength) {
                System.out.println("cacheLengthExceeded " + newCacheLength);

            }

            public void notificationsResent(final int resendCount) {
                System.out.println("notificationResent " + resendCount);
            }
        };
        boolean production = true;
        String certificate = "";
        String password = "";

        final String token = "";
        
        String body = "大飞哥儿:\"你好啊!!\"";
        
        String key = "";
        String val = "";
        
        final ApnsService svc = APNS.newService()
                .withAppleDestination(production)
                .withAutoAdjustCacheLength(true)
                .withReadTimeout(10000)
                .withReconnectPolicy(Provided.EVERY_HALF_HOUR)
                .withCert(certificate, password)
                .withDelegate(delegate)
                .build();

        final String payload = APNS.newPayload()
        		.alertBody(body)
        		.customField(key, val)
        		.build();

        svc.start();
        
        System.out.println("Sending message");
        final ApnsNotification goodMsg = svc.push(token, payload);
        System.out.println("Message id: " + goodMsg.getIdentifier());

        System.out.println("Getting inactive devices");

        final Map<String, Date> inactiveDevices = svc.getInactiveDevices();

        for (final Entry<String, Date> ent : inactiveDevices.entrySet()) {
            System.out.println("Inactive " + ent.getKey() + " at date " + ent.getValue());
        }
        System.out.println("Stopping service");
        svc.stop();
    }
}
