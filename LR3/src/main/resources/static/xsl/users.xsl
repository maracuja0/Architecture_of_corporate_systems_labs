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

                <form method="get" action="/api/positions">
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
                    <button type="submit">Выбрать</button>
                </form>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
