http://aubio.org/download

ControlAllServos has code that should work to control all of the 
servos (pin numbers need to be changed). Hopefully. Make sure
to test that code.

Commands currently sent over serial in the format: #, #, #, #, #

All values (0, 180). I think it'll work even if you don't send
5 values, but don't risk it?

The other directory has code that was confirmed to work for one
single servo. Turns out that although the continuous rotation 
servo takes values from (0, 180) inclusive, it doesn't have its
no movement value exactly at 90 like predicted. So, we're going
with PWM and a motor instead, which is known to be functional.