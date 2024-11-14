package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.domain.Payment;
import za.ac.cput.service.impl.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    public Payment save(Payment payment) {
        return paymentService.save(payment);
    }
}
