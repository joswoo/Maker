import RPi.GPIO as GPIO
import time
from datetime import datetime
from firebase import firebase


firebase = firebase.FirebaseApplication('https://maker-3cbf2.firebaseio.com', None)


GPIO.setwarnings(False)

IR = 18
LED = 23

GPIO.setmode(GPIO.BCM)
GPIO.setup(IR, GPIO.IN)
GPIO.setup(LED, GPIO.OUT)

def IR_Detect():
	now = datetime.now().hour
	#print(now)
	time1 = int(firebase.get('/AUTO','time1'))
	time2 = int(firebase.get('/AUTO','time2'))
	#print(time1)
	#print(type(time1))
	#print(time2)
	#print(type(time2))
	if time1 < time2:
		if now >= time1 and now <= time2:
			print(GPIO.input(IR))
			if GPIO.input(IR):
				firebase.patch('/AUTO',{'IR':1})
				GPIO.output(LED, GPIO.HIGH)
			else:
				firebase.patch('/AUTO',{'IR':0})
				GPIO.output(LED, GPIO.LOW)
			time.sleep(0.5)
		else:
			GPIO.output(LED, GPIO.LOW)
	elif time1 > time2:
		if now >= time1 or now <= time2:
			if GPIO.input(IR):
				firebase.patch('/AUTO',{'IR':1})
				GPIO.output(LED, GPIO.HIGH)
			else:
				firebase.patch('/ATUO',{'IR':0})
				GPIO.output(LED, GPIO.LOW)
			time.sleep(0.5)
		else:
			GPIO.output(LED, GPIO.LOW)
	

def Led():
	Switch = int(firebase.get('/SWITCH', None))
	#print(Switch)
	#print(type(Switch))
	if Switch == 0:
		GPIO.output(LED, GPIO.HIGH)
	
	elif Switch == 1:
		GPIO.output(LED, GPIO.LOW)
	
	elif Switch == 2:
		IR_Detect()

try:
	while True:
		Led()

except KeyboardInterrupt:
    GPIO.cleanup()