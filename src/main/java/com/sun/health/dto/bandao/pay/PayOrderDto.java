package com.sun.health.dto.bandao.pay;

public class PayOrderDto {
    private Amount amount;
    private String mchid;
    private String description;
    private String notify_url;
    private Payer payer;

    public PayOrderDto() {
        this(new Builder());
    }

    public PayOrderDto(Builder builder){
        this.amount = builder.amount;
        this.mchid = builder.mchid;
        this.description = builder.description;
        this.notify_url = builder.notify_url;
        this.payer = builder.payer;
    }

    public  Builder newBuilder(){
        return new Builder(this);
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public static class Builder {
        private Amount amount;
        private String mchid;
        private String description;
        private String notify_url;
        private Payer payer;

        public Builder() {
        }

        public PayOrderDto build(){
            return new PayOrderDto(this);
        }

        public Builder(PayOrderDto payOrderDto) {
            this.amount = payOrderDto.amount;
            this.mchid = payOrderDto.mchid;
            this.description = payOrderDto.description;
            this.notify_url = payOrderDto.notify_url;
            this.payer = payOrderDto.payer;
        }

        public Builder buildAmount(int total,String currency){
            this.amount = new Amount();
            amount.setTotal(total);
            amount.setCurrency(currency);
            return this;
        }

        public Builder buildMchid(String mchid){
            this.mchid = mchid;
            return this;
        }

        public Builder buildDescription(String description){
            this.description = description;
            return this;
        }

        public Builder buildNotifyUrl(String notify_url){
            this.notify_url = notify_url;
            return this;
        }

        public Builder buildPayer(String openid,String out_trade_no, String goods_tag, String appid){
            this.payer = new Payer();
            this.payer.setOpenid(openid);
            this.payer.setOut_trade_no(out_trade_no);
            this.payer.setGoods_tag(goods_tag);
            this.payer.setAppid(appid);
            return this;
        }
    }
}

class Payer {
    private String openid;
    private String out_trade_no;
    private String goods_tag;
    private String appid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}

class Amount {
    private int total;
    private String currency;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
