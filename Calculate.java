public class Calculate {

    private static final long FIRST_POW_INDEX = 2;
	private static final double DIVISION_BY_ZERO = 0.0;
    private static boolean resultIsDouble = false;

    public static void main(String[] args) {
        if ( args.length < 3) {
            errorMessage();
        } else {
            if  (args[1].length() != 3) {
                errorMessage();
            } else {
                char operand = args[1].charAt(1);

                Object first = parse(args[0]);
                Object second = parse(args[2]);
                Object result = calc(first, operand, second);

                System.out.println("Результат = " + result);
            }
        }
    }

    static Object parse(String variable) {
        if (tryParseLong(variable)) {
            return new Long(Long.parseLong(variable));
        } else {
            resultIsDouble = true;
            return new Double(Double.parseDouble(variable));
        }
    }

    static boolean tryParseLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    static Object calc(Object first, char operand, Object second) {
        switch (operand) {
            case '+': return sum(first, second);
            case '-': return difference(first, second);
            case '*': return multiply(first, second);
            case '/': return divide(first, second);
            case '^': return pow(first, second);
            default:  return new String("Введены некоректные данные. Пожалуйста запустите программу без параметров для получения справки.");
        }
    }

    static Object sum(Object first, Object second) {
        if (resultIsDouble) {

            Double var1 = new Double(first.toString());
            Double var2 = new Double(second.toString());

            return new Double(var1 + var2);
        }
        return new Long((Long) first + (Long) second);
    }

    static Object difference(Object first, Object second) {
        if (resultIsDouble) {

            Double var1 = new Double(first.toString());
            Double var2 = new Double(second.toString());

            return new Double(var1 - var2);
        }
        return new Long((Long) first - (Long) second);
    }

    static Object multiply(Object first, Object second) {
        if (resultIsDouble) {

            Double var1 = new Double(first.toString());
            Double var2 = new Double(second.toString());

            return new Double(var1 * var2);
        }
        return new Long((Long) first * (Long) second);
    }

    static Object divide(Object first, Object second) {
        Double var1 = new Double(first.toString());
        Double var2 = new Double(second.toString());
		
		if (var1 == DIVISION_BY_ZERO || var2 == DIVISION_BY_ZERO) {
			return new String("Оперцаия деления, в котором один, или оба операнда равны нулю невозможна");
		}
		
        if (resultIsDouble) {
            return new Double(var1 / var2);
        }
        if ((Long) first >= (Long) second) {
            return new Long((Long) first / (Long) second);
        }

        return new Double(var1 / var2);
    }

    static Object pow(Object first, Object second) {
        Double power = new Double(second.toString());
        if (power == 0) {
            return new Long(1);
        }

        if (resultIsDouble) {
            Double mathPow = new Double(first.toString());
            for (Long i = new Long(FIRST_POW_INDEX); i <= Math.abs(power); i++) {
                mathPow *= (Double) first;
            }
            if (power < 0) {
                return (1.0 / mathPow);
            }
            return mathPow;
        } else {
            Long mathPow = new Long((Long) first);
            for (Long i = new Long (FIRST_POW_INDEX); i <= Math.abs(power); i++) {
                mathPow *= (Long) first;
            }
            if (power < 0) {
                return (1.0 / mathPow);
            }
            return mathPow;
        }
    }
    static void errorMessage() {
        System.out.println("Вы ввели некоректные данные. Для корректной работы прогаммы необходимо ввести первый операнд, в одинарных кавычках указать операцию и ввести второй операнд.");
        System.out.println("Для сложения необходимо ввести '+'. Пример: 2 '+' 2");
        System.out.println("Для вычитания необходимо ввести '-'. Пример: 2 '-' 2");
        System.out.println("Для умножения необходимо ввести '*'. Пример: 2 '*' 2");
        System.out.println("Для деления необходимо ввести '/'. Пример: 2 '/' 2");
        System.out.println("Для возведения в степень необходимо ввести '^^'. Пример: 2 '^^' 2");
    }
}