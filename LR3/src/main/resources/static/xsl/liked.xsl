<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/likedResponse">
        <html lang="ru">
            <head>
                <meta charset="UTF-8"/>
                <title>Избранное</title>
            </head>
            <body>

                <h2>Избранное пользователя</h2>

                <a href="/">На главную</a>
                <br/>
                <a>
                    <xsl:attribute name="href">
                        <xsl:value-of select="concat('/api/positions?userId=', userId)"/>
                    </xsl:attribute>
                    Вернуться в магазин
                </a>

                <ul>
                    <xsl:for-each select="positions/positionEntity">
                        <li>
                            <b><xsl:value-of select="positionName"/></b> —
                            <xsl:value-of select="positionDesc"/>
                            (<xsl:value-of select="positionType"/>)

                            <form method="post" style="display:inline;">
                                <xsl:attribute name="action">
                                    <xsl:value-of select="concat('/api/users/', ../../userId, '/liked')"/>
                                </xsl:attribute>

                                <input type="hidden" name="positionId" value="{positionId}"/>

                                <input type="hidden" name="redirect">
                                    <xsl:attribute name="value">
                                        <xsl:value-of select="concat('/api/users/', ../../userId, '/liked')"/>
                                    </xsl:attribute>
                                </input>

                                <button type="submit" name="action" value="unlike">Убрать из избранного</button>
                            </form>
                        </li>
                    </xsl:for-each>
                </ul>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
