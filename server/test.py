import socket
server_ip = "0.0.0.0"
server_port = 8888

soc = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
soc.bind((server_ip,server_port))
print('UDP server started')
while True:
        try:
                data,addr = soc.recvfrom(1024)
                message = data.decode()
                #print(message)
                id, type, value = message.split(' ')
                #print(f'{id} {type} {value}')
                if type == 'dimmer':
                    print(f'dimmer device, id:{id} value:{value}')
                elif type == 'toggle':
                    print(f'toggle device, id:{id} value:{value}')
                value = value.encode()
                soc.sendto(value,addr)
        except KeyboardInterrupt:
                soc.close()