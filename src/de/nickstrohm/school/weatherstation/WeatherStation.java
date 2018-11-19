package de.nickstrohm.school.weatherstation;

import java.util.Random;

class WeatherStation {
    private static final int START_DEGREE = 8;
    private static final int POSSIBLE_DEGREE_CHANGE = 2;
    private static final int DAYS = 365;
    private static final int HOURS = 24;

    private int[][] _data;

    WeatherStation() {
        initArray();
        fillWithData();

        fillWithDefinedData();
    }

    private void initArray() {
        _data = new int[DAYS][HOURS];
        for (int i = 0; i < _data.length; i++) {
            _data[i] = new int[HOURS];
        }
    }

    private void fillWithData() {
        int degree = START_DEGREE;
        Random rng = new Random();

        for (int i = 0; i < DAYS * HOURS; i++) {
            int day = (int)Math.floor(i / (float)HOURS);
            int hour = i % HOURS;
            int change = rng.nextInt(POSSIBLE_DEGREE_CHANGE + 1);

            if (hour < (HOURS / 2)) {
                degree += change;
            }
            else {
                degree -= change;
            }

            _data[day][hour] = degree;
        }
    }

    private void fillWithDefinedData() {
        _data[0] = new int[]{10,11,12,13,12,14,15,16,16,16,17,17,18,19,20,22,24,24,26,24,20,16,15,15};
        _data[1] = new int[]{9,11,11,12,12,12,10,10,11,11,11,12,12,12,9,9,9,8,8,8,8,6,8,7};
        _data[2] = new int[]{8,9,9,9,9,10,10,10,11,11,15,17,20,26,26,29,25,25,25,23,23,23,20,20};
        _data[3] = new int[]{20,15,16,16,16,16,16,17,20,22,24,27,30,32,31,31,30,27,26,20,17,17,15,16};
        _data[4] = new int[]{15,15,16,14,14,15,16,17,21,21,22,23,23,23,22,22,21,20,20,19,17,17,16,16};
        _data[5] = new int[]{15,15,15,14,14,15,16,17,18,18,18,19,19,19,19,19,18,18,18,18,17,17,17,17};
        _data[6] = new int[]{18,18,18,18,18,18,19,20,20,24,24,24,25,25,25,25,23,20,20,20,18,17,17,17};
    }

    void printData() {
        for (int day = 0; day < DAYS; day++) {
            for (int hour = 0; hour < HOURS; hour++) {
                int degree = _data[day][hour];
                System.out.print(degree + " ");
            }

            System.out.print("\r\n");
        }

        System.out.println("Durschnittstemperatur Woche:      " + getAverageTemperatureForWeek());
        System.out.println("Durschnittstemperatur Montag:     " + getAverageTemperatureForDay(0));
        System.out.println("Durschnittstemperatur Dienstag:   " + getAverageTemperatureForDay(1));
        System.out.println("Durschnittstemperatur Mittwoch:   " + getAverageTemperatureForDay(2));
        System.out.println("Durschnittstemperatur Donnerstag: " + getAverageTemperatureForDay(3));
        System.out.println("Durschnittstemperatur Freitag:    " + getAverageTemperatureForDay(4));
        System.out.println("Durschnittstemperatur Samstag:    " + getAverageTemperatureForDay(5));
        System.out.println("Durschnittstemperatur Sonntag:    " + getAverageTemperatureForDay(6));
        System.out.println("Durschnittstemperatur  8.00:      " + getAverageTemperatureForHours(8));
        System.out.println("Durschnittstemperatur 12.00:      " + getAverageTemperatureForHours(12));
        System.out.println("Durschnittstemperatur 18.00:      " + getAverageTemperatureForHours(18));

        int[] coolestDay = getCoolestDayAndHour();
        int[] warmestDay = getWarmestDayAndHour();

        System.out.println("Tag niedrigste Temperatur:        " + coolestDay[0] + " " + coolestDay[1] + " " + _data[coolestDay[0]][coolestDay[1]]);
        System.out.println("Tag höchste Temperatur:           " + warmestDay[0] + " " + warmestDay[1] + " " + _data[warmestDay[0]][warmestDay[1]]);
    }

    private double getAverageTemperatureForDay(int day) {
        double result = 0;

        for (int hour = 0; hour < HOURS; hour++) {
            result += _data[day][hour];
        }

        result /= _data[day].length;

        return result;
    }

    private double getAverageTemperatureForWeek() {
        double result = 0;

        for (int day = 0; day < DAYS; day++) {
            for (int hour = 0; hour < HOURS; hour++) {
                result += _data[day][hour];
            }
        }

        result /= (DAYS * HOURS);

        return result;
    }

    private double getAverageTemperatureForHours(int hour) {
        double result = 0;

        for (int day = 0; day < DAYS; day++) {
            result += _data[day][hour];
        }

        result /= _data.length;

        return result;
    }

    private int[] getWarmestDayAndHour() {
        int resultDay = 0;
        int resultHour = 0;

        for (int day = 0; day < DAYS; day++) {
            for (int hour = 0; hour < HOURS; hour++) {
                if (_data[day][hour] > _data[resultDay][resultHour]) {
                    resultDay = day;
                    resultHour = hour;
                }
            }
        }

        return new int[] {resultDay, resultHour};
    }

    private int[] getCoolestDayAndHour() {
        int resultDay = 0;
        int resultHour = 0;

        for (int day = 0; day < DAYS; day++) {
            for (int hour = 0; hour < HOURS; hour++) {
                if (_data[day][hour] < _data[resultDay][resultHour]) {
                    resultDay = day;
                    resultHour = hour;
                }
            }
        }

        return new int[] {resultDay, resultHour};
    }
}
