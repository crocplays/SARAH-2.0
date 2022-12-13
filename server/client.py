import socket

class client:
    def __init__(self,host,port) -> None:
        self.HOST = host  # The server's hostname or IP address
        self.PORT = port  # The port used by the server
        self.SOC = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.SOC.connect((self.HOST, self.PORT))
        #s.sendall(b"Hello, world")
        #data = s.recv(1024)
    
    def send(self,data):
        self.SOC.sendall(data)
    
    def close(self):
        self.SOC.close()
