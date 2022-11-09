//import com.sun.deploy.config.JREInfo.clear
import java.util.Scanner

fun Wprowadz_liczbe():Int {
    var reader = Scanner(System.`in`)
    print("Enter a number: ")
    var obj = reader.nextInt()
    return obj
}