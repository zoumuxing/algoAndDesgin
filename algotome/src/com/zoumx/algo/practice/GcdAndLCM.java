package com.zoumx.algo.practice;
 
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.print.attribute.standard.Sides;
 
/**
 * 求两个正整数的最大公约数和最小公倍数
 * @author Jack
 * @date 2018-9-5
 * @version 1.0
 */
public class GcdAndLCM {
	public static void main(String[] args) {
		boolean flag = true;
		while (flag) {
			System.out.println("*****************求最大公约数与最小公倍数*****************");
			System.out.println("1:使用辗转相除法求两个正整数的最大公约数");
			System.out.println("2:使用等值算法(更相减损法)求两个正整数的最大公约数");
			System.out.println("3:使用相减法求两个正整数的最大公约数");
			System.out.println("4:使用穷举法求两个正整数的最大公约数");
			System.out.println("5:使用公式法求两个正整数的最小公倍数");
			System.out.println("6:使用短除法求两个正整数的最小公倍数");
			System.out.println("7:使用辗转相除法求三个正整数的最大公约数");
			System.out.println("8:使用公式法求三个正整数的最小公倍数");
			System.out.println("请选择一种计算方法:(1-8),退出系统请按(N/n)");
			Scanner sc = new Scanner(System.in);
			boolean isValidate;
			switch (sc.next()) {
			case "1":
				String[] numbers = {};
				boolean isValidate1;
				boolean isValidate2;
				do {
					numbers = inputNumber();// 从键盘输入数据
					isValidate1 = validate(numbers[0]); // 检测输入数字是否正确
					isValidate2 = validate(numbers[1]);
				} while (!isValidate1 || !isValidate2);
				int result = divisionAlgorithm(new Integer(numbers[0]), new Integer(numbers[1]));
				System.out.println(numbers[0] + "和" + numbers[1] + "的最大公约数为:" + result);
				break;
			case "2":
				do {
					numbers = inputNumber();
					isValidate1 = validate(numbers[0]);
					isValidate2 = validate(numbers[1]);
				} while (!isValidate1 || !isValidate2);
				result = equalAlgorithm(new Integer(numbers[0]), new Integer(numbers[1]));
				System.out.println(numbers[0] + "和" + numbers[1] + "的最大公约数为:" + result);
				break;
			case "3":
				do {
					numbers = inputNumber();
					isValidate1 = validate(numbers[0]);
					isValidate2 = validate(numbers[1]);
				} while (!isValidate1 || !isValidate2);
				result = subtractionAlgorithm(new Integer(numbers[0]), new Integer(numbers[1]));
				System.out.println(numbers[0] + "和" + numbers[1] + "的最大公约数为:" + result);
				break;
			case "4":
				do {
					numbers = inputNumber();
					isValidate1 = validate(numbers[0]);
					isValidate2 = validate(numbers[1]);
				} while (!isValidate1 || !isValidate2);
				result = exhaustAlgorithm(new Integer(numbers[0]), new Integer(numbers[1]));
				System.out.println(numbers[0] + "和" + numbers[1] + "的最大公约数为:" + result);
				break;
			case "5":
				do {
					numbers = inputNumber();
					isValidate1 = validate(numbers[0]);
					isValidate2 = validate(numbers[1]);
				} while (!isValidate1 || !isValidate2);
				result = formulaAlgorithm(new Integer(numbers[0]), new Integer(numbers[1]));
				System.out.println(numbers[0] + "和" + numbers[1] + "的最小公倍数为:" + result);
				break;
			case "6":
				do {
					numbers = inputNumber();
					isValidate1 = validate(numbers[0]);
					isValidate2 = validate(numbers[1]);
				} while (!isValidate1 || !isValidate2);
				result = shortDivisionAlgorithm(new Integer(numbers[0]), new Integer(numbers[1]));
				System.out.println(numbers[0] + "和" + numbers[1] + "的最小公倍数为:" + result);
				break;
			case "7":
				do {
					numbers = inputNumber();
					isValidate1 = validate(numbers[0]);
					isValidate2 = validate(numbers[1]);
				} while (!isValidate1 || !isValidate2);
				System.out.println("请输入第三个正整数:");
				Scanner scanner = new Scanner(System.in);
				int num3 = scanner.nextInt();
				result = threeParametersGcd(new Integer(numbers[0]), new Integer(numbers[1]), num3);
				System.out.println(numbers[0] + ", " + numbers[1] + "和" + num3 + "的最大公约数为:" + result);
				break;
			case "8":
				do {
					numbers = inputNumber();
					isValidate1 = validate(numbers[0]);
					isValidate2 = validate(numbers[1]);
				} while (!isValidate1 || !isValidate2);
				System.out.println("请输入第三个正整数:");
				scanner = new Scanner(System.in);
				num3 = scanner.nextInt();
				result = threeParametersLCM(new Integer(numbers[0]), new Integer(numbers[1]), num3);
				System.out.println(numbers[0] + ", " + numbers[1] + "和" + num3 + "的最小公倍数为:" + result);
				break;
			case "Y":
				break;
			case "y":
				break;
			case "N":
				flag = false;
				sc.close();// 关闭资源
				break;
			case "n":
				flag = false;
				sc.close();
				break;
			default:
				break;
			}
		}
	}
 
	/**
	 * 使用辗转相除法求两个正整数的最大公约数
	 * @param num1 正整数1
	 * @param num2 正整数2
	 * @return 两个正整数的最大公约数
	 */
	public static int divisionAlgorithm(int num1, int num2) {
		// 求两个正整数中最大的数
		int max = maxNumber(num1, num2);
		int result = 0;
		if (max == num1) {
			// 较大的数除以较小的数并取余数
			int remainder = num1 % num2;
			while (remainder != 0) {
				num1 = num2;
				num2 = remainder;
				remainder = num1 % num2;
			}
			result = num2;
		} else if (max == num2) {
			int remainder = num2 % num1;
			while (remainder != 0) {
				num2 = num1;
				num1 = remainder;
				remainder = num2 % num1;
			}
			result = num1;
		}
		return result;
	}


	/**
	 * 辗转相除法 比较大小，不停整除取模，直到不能取模为止
	 * @param num1
	 * @param num2
	 * @return
	 */
	private static int divisionAlgorithm1(int num1,int num2) {
        int max = maxNumber(num1,num2);
        int result = 0;
        if(max==num1) {
        	int remainder = num1%num2;
            while (remainder > 0) {
            	num1 = num2;
            	num2 = remainder;
            	remainder = num1 % num2;
			}
            result = num1;
		}else {
        	int remainer = num2%num1;
        	while (remainer>0) {
        		num2 = num1;
        		num1 = remainer;
        		remainer = num2 %num1;
			}
        	result = num2;
		}
		return result;
	}

	/**
	 * 其实用递归求解更方便，不用考虑谁大谁小，因为最终除的那个值肯定是大于0的。
	 * @param x
	 * @param y
	 * @return
	 */
	private int gcd(int x,int y) {
		return y > 0 ?gcd(y,x%y):x;
	}

 
	/**
	 * 使用等值算法(更相减损法)求两个正整数的最大公约数
	 * @param num1 正整数1
	 * @param num2 正整数2
	 * @return 两个正整数的最大公约数
	 */
	public static int equalAlgorithm(int num1, int num2) {
		// 计算除了多少次2
		int count = 0;
		while (num1 % 2 == 0 && num2 % 2 == 0) {
			num1 = num1 / 2;
			num2 = num2 / 2;
			count++;
		}
		int max = maxNumber(num1, num2);
		// 两个数之差
		int differ = 0;
		if (max == num1) {
			while ((differ = num1 - num2) != num2) {
				if (differ > num2) {
					num1 = differ;
				} else {
					num1 = num2;
					num2 = differ;
				}
			}
		} else {
			while ((differ = num2 - num1) != num1) {
				if (differ > num1) {
					num2 = differ;
				} else {
					num2 = num1;
					num1 = differ;
				}
			}
		}
		if (count != 0) {
			return 2 * count * differ;
		} else {
			return differ;
		}
	}
 
	/**
	 * 使用相减法求两个正整数的最大公约数
	 * @param num1 正整数1
	 * @param num2 正整数2
	 * @return 两个正整数的最大公约数
	 */
	public static int subtractionAlgorithm(int num1, int num2) {
		while (num1 != num2) {
			if (num1 > num2) {
				num1 -= num2;
			} else {
				num2 -= num1;
			}
		}
		return num1;
	}
 
	/**
	 * 使用穷举法求两个正整数的最大公约数
	 * @param num1 正整数1
	 * @param num2 正整数2
	 * @return 两个正整数的最大公约数
	 */
	public static int exhaustAlgorithm(int num1, int num2) {
		int result = 0;
		int max = maxNumber(num1, num2);
		for (int index = 1; index <= max; index++) {
			if (num1 % index == 0 && num2 % index == 0) {
				result = index;
			}
		}
		return result;
	}
 
	/**
	 * 使用公式法求两个正整数的最小公倍数
	 * @param num1 正整数1
	 * @param num2 正整数2
	 * @return 两个正整数的最小公倍数
	 */
	public static int formulaAlgorithm(int num1, int num2) {
		// 先求出最大公约数
		int gcd = divisionAlgorithm(num1, num2);
		return num1 * num2 / gcd;
	}
 
	/**
	 * 使用短除法求两个正整数的最小公倍数
	 * @param num1 正整数1
	 * @param num2 正整数2
	 * @return 两个正整数的最小公倍数
	 */
	public static int shortDivisionAlgorithm(int num1, int num2) {
		int index, max;
		int result = 1;
		// 循环结束标志
		boolean flag;
		max = maxNumber(num1, num2);
		for (index = 2; index <= max; index++) {
			flag = true;
			while (flag) {
				flag = false;
				if (num1 % index == 0) {
					num1 = num1 / index;
					flag = true;
				}
				if (num2 % index == 0) {
					num2 = num2 / index;
					flag = true;
				}
				if (flag == true)
					result *= index;
			}
			max = maxNumber(num1, num2);
		}
		return result;
	}
 
	/**
	 * 使用辗转相除法求三个正整数的最大公约数
	 * @param num1 正整数1
	 * @param num2 正整数2
	 * @param num3 正整数3
	 * @return 三个正整数的最大公约数
	 */
	public static int threeParametersGcd(int num1, int num2, int num3) {
		// 先求两个数的最大公约数
		int argument = divisionAlgorithm(num1, num2);
		int result = divisionAlgorithm(argument, num3);
		return result;
	}
 
	/**
	 * 使用公式法求三个正整数的最小公倍数
	 * @param num1 正整数1
	 * @param num2 正整数2
	 * @param num3 正整数3
	 * @return 三个正整数的最小公倍数
	 */
	public static int threeParametersLCM(int num1, int num2, int num3) {
		// 先求两个数的最小公倍数
		int argument = formulaAlgorithm(num1, num2);
		int result = formulaAlgorithm(argument, num3);
		return result;
	}
 
	/**
	 * 求两个整数中最大的数
	 * @param num1  整数1
	 * @param num2  整数2
	 * @return 两个整数中最大的数
	 */
	public static int maxNumber(int num1, int num2) {
		if (num1 > num2) {
			return num1;
		} else {
			return num2;
		}
	}
 
	/**
	 * 求两个整数中最小的数
	 * @param num1 整数1
	 * @param num2 整数2
	 * @return 两个整数中最小的数
	 */
	public static int minNumber(int num1, int num2) {
		if (num1 < num2) {
			return num1;
		} else {
			return num2;
		}
	}
 
	/**
	 * 使用正则表达式检测数据合法性
	 * @param num 被检测数据
	 * @return 数据是否合法,true表示合法,false表示非法
	 */
	public static boolean validate(String num) {
		// 使用正则表达式测试输入是否为正整数
		Pattern pattern = Pattern.compile("^\\+?[1-9][0-9]*$");
		Matcher match = pattern.matcher(num);
		if (!match.matches()) {
			System.out.println("输入数字有错误,请重新输入!");
			return false;
		}
		return true;
	}
 
	/**
	 * 从键盘输入数据
	 * @return 返回输入的数据
	 */
	public static String[] inputNumber() {
		String[] numbers = new String[2];
		System.out.println("请输入第一个正整数:");
		Scanner sc = new Scanner(System.in);
		numbers[0] = sc.next();
		System.out.println("请输入第二个正整数:");
		numbers[1] = sc.next();
		return numbers;
	}
}