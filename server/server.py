import socket

class server:
    def __init__(self,port):
        self.HOST = '127.0.0.1'
        self.PORT = port
        self.data = 0
        self.SOC = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.SOC.bind((self.HOST, self.PORT))
        self.SOC.listen()
        self.conn, self.addr = self.SOC.accept()
        with self.conn:
                print(f"Connected by {self.addr}")
                #conn.sendall(b"connected")
                while True:
                    self.data = self.conn.recv(1024)
                    print(self.data)
                    if not self.data:
                        break


    # def run(self):
    #         with self.conn:
    #             print(f"Connected by {self.addr}")
    #             #conn.sendall(b"connected")
    #             while True:
    #                 self.data = self.conn.recv(1024)
    #                 print(self.data)
    #                 if not self.data:
    #                     break
    #             #conn.sendall(data)
                    
    def getData(self):
        return self.data
    
    def close(self):
        self.SOC.close()
    
    
                
