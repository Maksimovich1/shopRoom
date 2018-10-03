package com.mamba.shop.controller;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/payment")
public class PaymentStripeController {

    @RequestMapping
    public String stripe(
            @RequestParam Map<String, String> request,
            Model model
            ){

        String token = request.get("stripeToken");
        if (token != null){
            System.out.println("_______Токен не пустой" + token);

            Stripe.apiKey = "sk_test_3oGUSnkEBKqufF2Kv3hQO3BQ";
            try {

                Map<String, Object> chargeParams = new HashMap<>();
                chargeParams.put("amount", 100); // amount in cents, again
                chargeParams.put("currency", "eur");
                chargeParams.put("source", token);
                Charge charge = Charge.create(chargeParams);
                chargeParams.put("description", "Example charge");
            } catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException | APIException e) {
                e.printStackTrace();
            }

        }
        else System.out.println("_____Токен пуст!!");

        return "paymentResult";
    }

}
