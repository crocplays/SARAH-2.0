import client,server,deviceManager
import signal,readchar,sys


def cleanup():
    serv.close

def signal_handler(signum, frame):
    print(f"Received {signum}! Shutting down...")
    cleanup() # create your own clean up function
    sys.exit()


if __name__ == "__main__":
    #signal.signal(signal.SIGBREAK, signal_handler)
    serv = server.server(8888)
    serv.run