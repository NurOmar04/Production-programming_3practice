import java.sql.*;
import java.util.Scanner;

public class Task_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Загружаем драйвер
            Class.forName("org.sqlite.JDBC");

            // Подключаемся к БД (если файла books.db нет, он создастся автоматически)
            Connection conn = DriverManager.getConnection("jdbc:sqlite:books.db");
            Statement stmt = conn.createStatement();

            // Создаём таблицу (если ещё не создана)
            stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT NOT NULL, " +
                    "author TEXT NOT NULL, " +
                    "year INTEGER NOT NULL)");

            while (true) {
                System.out.println("\nМеню:");
                System.out.println("1. Добавить книгу");
                System.out.println("2. Удалить последнюю книгу");
                System.out.println("3. Изменить автора первой книги");
                System.out.println("4. Найти книги по фильтру и сортировке");
                System.out.println("5. Показать все книги");
                System.out.println("6. Удалить книгу по ID");
                System.out.println("7. Очистить всю таблицу книг");
                System.out.println("0. Выход");
                System.out.print("Выберите пункт: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // очистка буфера

                if (choice == 0) {
                    break;
                }

                switch (choice) {
                    case 1: // Добавить книгу
                        System.out.print("Введите название: ");
                        String title = scanner.nextLine();
                        System.out.print("Введите автора: ");
                        String author = scanner.nextLine();
                        System.out.print("Введите год: ");
                        int year = scanner.nextInt();

                        PreparedStatement ps = conn.prepareStatement(
                                "INSERT INTO books (title, author, year) VALUES (?, ?, ?)");
                        ps.setString(1, title);
                        ps.setString(2, author);
                        ps.setInt(3, year);
                        ps.executeUpdate();
                        System.out.println("Книга добавлена.");
                        break;

                    case 2: // Удалить последнюю книгу
                        stmt.executeUpdate("DELETE FROM books WHERE id = (SELECT MAX(id) FROM books)");
                        System.out.println("Последняя книга удалена.");
                        break;

                    case 3: // Изменить автора первой книги
                        System.out.print("Введите нового автора: ");
                        String newAuthor = scanner.nextLine();
                        stmt.executeUpdate("UPDATE books SET author='" + newAuthor + "' WHERE id=(SELECT MIN(id) FROM books)");
                        System.out.println("Автор первой книги изменён.");
                        break;

                    case 4: // Фильтр + сортировка
                        System.out.print("Введите автора для фильтра: ");
                        String filterAuthor = scanner.nextLine();
                        System.out.print("Введите год для фильтра: ");
                        int filterYear = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Введите поле для сортировки (title/author/year): ");
                        String sortField = scanner.nextLine();
                        System.out.print("Сортировать по возрастанию? (yes/no): ");
                        String asc = scanner.nextLine();

                        String query = "SELECT * FROM books WHERE author=? AND year=? ORDER BY " +
                                sortField + (asc.equalsIgnoreCase("yes") ? " ASC" : " DESC");

                        PreparedStatement psFilter = conn.prepareStatement(query);
                        psFilter.setString(1, filterAuthor);
                        psFilter.setInt(2, filterYear);
                        ResultSet rs = psFilter.executeQuery();

                        System.out.println("Результаты поиска:");
                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + " | " +
                                    rs.getString("title") + " | " +
                                    rs.getString("author") + " | " +
                                    rs.getInt("year"));
                        }
                        break;

                    case 5: // Показать все книги
                        printBooks(conn);
                        break;

                    case 6: // Удалить книгу по ID
                        printBooks(conn);
                        System.out.print("Введите ID книги для удаления: ");
                        int delId = scanner.nextInt();
                        int rowsDeleted = stmt.executeUpdate("DELETE FROM books WHERE id=" + delId);
                        if (rowsDeleted > 0) {
                            System.out.println("Книга с ID " + delId + " удалена.");
                        } else {
                            System.out.println("Книга с таким ID не найдена.");
                        }
                        break;

                    case 7: // Очистить таблицу
                        stmt.executeUpdate("DELETE FROM books");
                        System.out.println("Все книги удалены.");
                        break;

                    default:
                        System.out.println("Неверный выбор!");
                }
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для вывода всех книг
    public static void printBooks(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM books");

        System.out.println("\n==== Список книг ====");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " +
                    rs.getInt("year"));
        }
    }
}
