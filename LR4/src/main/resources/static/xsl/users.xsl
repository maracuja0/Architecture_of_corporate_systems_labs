<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/users">
        <html lang="ru">
            <head>
                <meta charset="UTF-8"/>
                <title>Пользователи</title>
            </head>
            <body>
                <h2>Выберите пользователя</h2>
                <a href="/">На главную</a>

                <form>
                    <label>Пользователь:</label>
                    <select name="userId">
                        <xsl:for-each select="user">
                            <option value="{@userId}">
                                <xsl:value-of select="userFirstName"/>
                                <xsl:text> </xsl:text>
                                <xsl:value-of select="userLastName"/>
                            </option>
                        </xsl:for-each>
                    </select>

                    <br/><br/>

                    <!-- Кнопка выбора -->
                    <button type="submit"
                            formaction="/api/positions"
                            formmethod="get">
                        Выбрать
                    </button>

                    <!-- Кнопка удаления -->
                    <button type="submit"
                            formaction="/api/users/delete"
                            formmethod="post"
                            style="color:red;"
                            onclick="return confirm('Удалить выбранного пользователя?');">
                        Удалить пользователя
                    </button>
                </form>


                <h2>Создать нового пользователя</h2>

                <form method="post" action="/api/users/create">
                    <label>Имя: </label>
                    <input type="text" name="userFirstName" required="required"/>
                    <br/>

                    <label>Фамилия: </label>
                    <input type="text" name="userLastName" required="required"/>
                    <br/>

                    <label>Пароль: </label>
                    <input type="password" name="userPassword" required="required"/>
                    <br/>

                    <label>Email: </label>
                    <input type="email" name="userEmail"/>
                    <br/>

                    <label>Телефон: </label>
                    <input type="text" name="userPhone"/>
                    <br/>

                    <label>Пол:</label>
                    <select name="userGender">
                        <option value="">Не выбрано</option>
                        <option value="true">Мужской</option>
                        <option value="false">Женский</option>
                    </select>
                    <br/>

                    <label>Дата рождения:</label>
                    <input type="date" name="userBDay"/>
                    <br/>

                    <button type="submit">Создать</button>
                </form>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
