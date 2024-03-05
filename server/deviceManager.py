class dimmerDevice:
    def __init__(self, device_name, device_id) -> None:
        self.device_name = device_name
        self.device_id = device_id


class toggleDevie:
    def __init__(self, device_name, device_id) -> None:
        self.device_name = device_name
        self.device_id = device_id
        self.power_status = False


class rgb_bulb:
    def __init__(self, device_name, device_id) -> None:
        pass
