# TrainTicketReservation

Checking for Available Tickets
Book Ticket
  Booking Based on preferred berth if available,else book available berth
  Berth not available means book RAC
  RAC not available means book WL
Cancel Ticket
  If berth ticket booked means,RAC first ticket will be confirmed and WL first will move to RAC
  If RAC cancelled means,WL will added to RAC queue
  After cancelling,increase seat availability
Booking history
  display all booked tickets
Ticket Status
  display a ticket based on PNR
