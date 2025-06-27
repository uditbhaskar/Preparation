package solidPrincipal

/**
 * Suppose your NotificationSender class has a when(type) block to handle
 * different kinds of notifications (EMAIL, SMS, PUSH).
 *
 */


class NotificationSenders(){

}


interface SendNotification {
    fun send()
}

class SMSNotification : SendNotification {
    override fun send() {
        println("Send SMS notification.")
    }
}

class EmailNotification: SendNotification {
    override fun send() {
        println("Send Email notification.")
    }
}

class PushNotification: SendNotification{
    override fun send() {
        println("Send Push notification.")
    }
}

class ArunNotification: SendNotification{
    override fun send() {
        println("Send Arun notification.")
    }
}


class NotificationSender {
    fun sendNotifications(send: SendNotification){
        send.send()
    }
}

fun main(){
    val sendForSMS = SMSNotification()
    val sendForEmail = EmailNotification()
    val sendForPush = PushNotification()
    val arunNotification = ArunNotification()
    val obj  = NotificationSender()
    obj.sendNotifications(arunNotification)
    obj.sendNotifications(sendForPush)

    val paymentFromStrip = Strip()
    val paymentFromGooglePay = GooglePay()
    val paymentFromPayPal = PayPal()
    val objPayment  = PaymentProcessor()
    objPayment.processPayment(paymentFromPayPal)
    objPayment.processPayment(paymentFromStrip)
    objPayment.processPayment(paymentFromGooglePay)
}

interface Payment {
    fun process()
}

class Strip : Payment {
    override fun process() {
        println("Payment through Stripe.")
    }
}

class GooglePay : Payment {
    override fun process() {
        println("Payment through GooglePay.")
    }
}
class PayPal : Payment {
    override fun process() {
        println("Payment through PayPal.")
    }
}

class PaymentProcessor {
    fun processPayment(payment: Payment){
        payment.process()
    }
}