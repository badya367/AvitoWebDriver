public enum Price {
    По_умолчанию("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/select/option[1]"),
    Дешевле("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/select/option[2]"),
    Дороже("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/select/option[3]"),
    По_дате("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/select/option[4]");
    public String prices;

    Price(String prices) {
        this.prices = prices;
    }
}
