#! /usr/bin/env python
from deviceManager import *
import sys, socket, os, yaml

server_ip = "0.0.0.0"
server_port = 8888
basedir = os.path.dirname(os.path.abspath(__file__))
devices_yaml = os.path.join(basedir, "devices.yml")
devices = []


def cleanup(serv):
    serv.close()


def init_devices():
    pass


def init():
    pass


def main():
    serv = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    serv.bind((server_ip, server_port))
    print("Started SARAH Server...")
    while True:
        data, addr = serv.recvfrom(1024)
        data = data.decode()
        deviceId, deviceType, deviceValue = data.split(" ")
        # do stuff
        if deviceType == "dimmer":
            pass
        elif deviceType == "toggle":
            pass
        elif deviceType == "rgb_bulb":
            pass
        deviceValue = deviceValue.encode()
        serv.sendto(deviceValue, addr)


if __name__ == "__main__":
    main()
