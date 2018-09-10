package enums;

public enum TypeEnum {

    First(1,"First"),
    Second(2,"Second");

    public Integer type;
    public String typeShow;

    TypeEnum(Integer type,String typeShow){
        this.type = type;
        this.typeShow = typeShow;
    }

    public static TypeEnum getTypeEnum(Integer data){
        for (TypeEnum typeEnum: TypeEnum.values()){
            if (typeEnum.type == data){
                return typeEnum;
            }
        }
        return null;
    }
}
