package Tipos;

public enum TipoID {
    /*O toString é sobrescrito para funcionar como o prefixo do controle de ID*/
    USUARIO {
        @Override
        public String toString() {
            return "USER-";
        }
    },
    ITEM {
        @Override
        public String toString(){
            return "ITEM-";
        }
    },
    PEDIDO{
        @Override
        public String toString(){
            return "PEDI-";
        }
    }
}
