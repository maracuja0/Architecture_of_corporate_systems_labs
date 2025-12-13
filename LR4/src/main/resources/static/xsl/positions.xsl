<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html" encoding="UTF-8"/>

<xsl:template match="/positions">
    <html lang="ru">
        <head>
            <meta charset="UTF-8"/>
            <title>Все позиции</title>
        </head>
        <body>

            <h3>
                Пользователь:
                <xsl:value-of select="user/userFirstName"/>
                <xsl:text> </xsl:text>
                <xsl:value-of select="user/userLastName"/>
            </h3>
            <a href="/">На главную</a>
            <br/>
            <a>
                <xsl:attribute name="href">
                    <xsl:value-of select="concat('/api/users/', user/userId, '/liked')"/>
                </xsl:attribute>
                Перейти в избранное
            </a>

            <h2>Все позиции</h2>
            <ul>
                <xsl:for-each select="position">
                    <li>
                        <b><xsl:value-of select="positionName"/></b> —
                        <xsl:value-of select="positionDesc"/>
                        (<xsl:value-of select="positionType"/>)

                        <form method="post" style="display:inline;">
                            <xsl:attribute name="action">
                                <xsl:value-of select="concat('/api/users/', /positions/user/userId, '/liked')"/>
                            </xsl:attribute>

                            <input type="hidden" name="positionId" value="{positionId}"/>
                            <input type="hidden" name="redirect">
                                <xsl:attribute name="value">
                                    <xsl:value-of select="concat('/api/positions?userId=', /positions/user/userId)"/>
                                </xsl:attribute>
                            </input>

                            <xsl:choose>
                                <xsl:when test="liked='true'">
                                    <button type="submit" name="action" value="unlike">Убрать из избранного</button>
                                </xsl:when>
                                <xsl:otherwise>
                                    <button type="submit" name="action" value="like">Добавить в избранное</button>
                                </xsl:otherwise>
                            </xsl:choose>
                        </form>
                    </li>
                </xsl:for-each>
            </ul>
        </body>
    </html>
</xsl:template>
</xsl:stylesheet>
