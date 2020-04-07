package com.wangshao.disruptor.generate2;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import com.wangshao.disruptor.generate1.Trade;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author liutao
 * @create 2020-03-25-23:09
 */


public class TradePublisher implements Runnable{

    Disruptor<Trade> disruptor;
    private CountDownLatch latch;

    private static int LOOP=1;//模拟百万次交易的放生

    public TradePublisher(Disruptor<Trade> disruptor, CountDownLatch latch) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        TradeEventTranslator tradeEventTranslator = new TradeEventTranslator();
        for (int i = 0; i < LOOP; i++) {
            disruptor.publishEvent(tradeEventTranslator);
        }
        latch.countDown();
    }

    class TradeEventTranslator implements EventTranslator<Trade>{

        private Random random =new Random();

        @Override
        public void translateTo(Trade event, long sequence) {
            this.generateTrade(event);
        }

        private Trade generateTrade(Trade trade){
            trade.setPrice(random.nextDouble()*9999);
            return trade;
        }
    }
}
