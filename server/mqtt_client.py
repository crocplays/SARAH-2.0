import paho.mqtt.client as mqtt


class mqtt_client:
    def __init__(self, callback_version, id: str) -> None:
        self._client = mqtt.Client(callback_version, client_id=id)
        self._client.user_data_set([])
        self._client.on_message = self.on_message
        self._client.on_connect = self.on_connect
        self._client.on_subscribe = self.on_subscribe
        self._client.on_unsubscribe = self.on_unsubscribe
        self._client.on_publish = self.on_publish

    def connect(self, host: str, port=None):
        if port:
            self._client.connect(host, port)
        else:
            self._client.connect(host)

    def publish(self, topic: str, payload: str):
        self._client.publish(topic=topic, payload=payload)

    def subscribe(self, topic: str):
        self._client.subscribe(topic=topic)

    def loop_start(self):
        self._client.loop_start()

    def loop_stop(self):
        self._client.loop_stop()

    def on_message(self, client, userdata, message):
        print(
            f'Recieved message on topic:{message.topic}\nmessage recieved: {str(message.payload.decode("utf-8"))}'
        )

    def on_connect(self, client, userdata, flags, reason_code, properties):
        if reason_code.is_failure:
            print(
                f"Failed to connect: {reason_code}. loop_forever() will retry connection"
            )
        else:
            # self._client.subscribe("$SYS/#")
            pass

    def on_subscribe(self, client, userdata, mid, reason_code_list, properties):
        if reason_code_list[0].is_failure:
            print(f"Broker rejected you subscription: {reason_code_list[0]}")
        else:
            print(f"Broker granted the following QoS: {reason_code_list[0].value}")

    def on_unsubscribe(self, client, userdata, mid, reason_code_list, properties):
        if len(reason_code_list) == 0 or not reason_code_list[0].is_failure:
            print("unsubscribe succeeded (if SUBACK is received in MQTTv3 it success)")
        else:
            print(f"Broker replied with failure: {reason_code_list[0]}")
        self._client.disconnect()

    def on_publish(self, client, userdata, mid, reason_code, properties):
        try:
            userdata.remove(mid)
        except KeyError:
            print("on_publish() is called with a mid not present in unacked_publish")
            print("This is due to an unavoidable race-condition:")
            print("* publish() return the mid of the message sent.")
            print("* mid from publish() is added to unacked_publish by the main thread")
            print("* on_publish() is called by the loop_start thread")
            print(
                "While unlikely (because on_publish() will be called after a network round-trip),"
            )
            print(" this is a race-condition that COULD happen")
            print("")
            print(
                "The best solution to avoid race-condition is using the msg_info from publish()"
            )
            print(
                "We could also try using a list of acknowledged mid rather than removing from pending list,"
            )
            print("but remember that mid could be re-used !")
