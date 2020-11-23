import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Caballos {
    static final int n = 8; //TAMAÑO DEL TABLERO
    static final int m = (n + 1);
    private int[][] tablero = new int[m][m];
    private boolean sucess;
    //LOS 8 MOVIMIENTOS QUE PUEDE REALIZAR EL CABALLO:
    private int [][] salto = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    private int xo, yo; //LOS PUNTOS DE PARTIDA DEL CABALLO

    //CONTRUCTOR!!!
    public Caballos(int x, int y) { //X Y Y POSICION INICAL DEL CABALLO
        if((x < 1) || ( x > n) || (y < 1) || (y > n)){ //PARA QUE NO SE SALGA DE LOS PARAMETROS DEL TABLERO
            System.out.println("Coordenadas fuera del tablero establecido");
        }
        xo = x;
        yo = y;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                tablero[i][j] = 0; //LLENAMOS DE CEROS
            }
        }
        tablero[xo][yo] = 1; //LA PRIMERA POSICION EN LA QUE ESTUVO
        sucess = false;
    }

    public boolean solveProblem(){
        //LLAMARA REPETIDAMENTE LOS SALTOS DEL CABALLO
        jumpHorse(xo, yo, 2);
        return sucess;
    }

    public void jumpHorse(int x, int y, int i){ //RECIBE LAS POSICIONES Y UN CONTADOR
        //CUENTA LA CANTIDAD DE SALTOS EXITOSOS
        int nx, ny;
        int k = 0; //K INICIALIZA EL POSIBLE CONJUNTO DE MOVIMIENTOS, CONTADOR PARA LA MATRIZ SALTO
        do{
            k++;
            nx = x + salto[k-1][0];
            ny = y + salto[k-1][1];
            //DETERMINA SI NUEVAS COORDENADAS SON ACEPTABLES
            if((nx >= 1) && (nx <= n) && (ny >= 1) && (ny <= n) && tablero[nx][ny] == 0){ //0 POSISICON NO VISITADA
                tablero[nx][ny] = i; //ANOTAMOS ESE MOVIMIENTO
                if(i < n * n){ //I < 64
                    jumpHorse(nx, ny, i+1); //SE ANALIZA SI SE HA COMPLETADO LA SOLUCION
                    if(!sucess){ //SI NO SE ALCANZO LA SOLUCION
                        tablero[nx][ny] = 0; //BORRAMOS LA POSICION ANTERIOR
                    }
                }else{
                    sucess = true; //TABLERO CUBIERTO
                }
            }
        }while((k < 8) && !sucess);
    }

    public void printTablero(){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                System.out.print(tablero[i][j]+" \t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
       int x, y;
       boolean solution;
       BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Posicion inicial del caballo. ");
            System.out.print(" x = ");
            x = Integer.parseInt(entrada.readLine());
            System.out.print(" y = ");
            y = Integer.parseInt(entrada.readLine());
            Caballos miCaballo = new Caballos(x, y);
            solution = miCaballo.solveProblem();
            if (solution) {
                System.out.println();
                miCaballo.printTablero();
            }
        }catch (Exception m) {
        System.out.println("No se pudo probar el algoritmo, " + m);
        }
    }
}
