package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.PaymentRepository;
import za.ac.cput.service.IPaymentService;
@Service
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
