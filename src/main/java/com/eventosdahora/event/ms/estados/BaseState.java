package com.eventosdahora.event.ms.estados;

import com.eventosdahora.event.ms.kafka.Order;
import com.eventosdahora.event.ms.kafka.OrderState;

public abstract class BaseState {

    abstract void enterState(Order order);

    abstract void negado(Order order, OrderState reason);

    abstract void aprovado(Order order, OrderState reason);

}
