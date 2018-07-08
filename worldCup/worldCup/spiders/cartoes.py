# -*- coding: utf-8 -*-
import scrapy


class CartoesSpider(scrapy.Spider):
    name = 'cartoes'
    allowed_domains = ['fifa.com']
    start_urls = ['http://www.fifa.com/fifa-tournaments/statistics-and-records/worldcup/teams/index.html']

    def parse(self, response):
        for tabela5 in response.xpath('//table[@class="table tbl-t-most-cards"]/tbody/tr'):
            yield{
                'selecao': tabela5.xpath('td[@class="tbl-teamname teamname-link"]/a/div/div[@class="t-n"]/span/text()').extract_first(),
                'cartoes': tabela5.xpath('td[@class="tbl-cards-tot"]/span/text()').extract_first(),
                'amarelos': tabela5.xpath('td[@class="tbl-y"]/span/text()').extract_first(),
                'dois amarelos': tabela5.xpath('td[@class="tbl-2yc"]/span/text()').extract_first(),
                'vermelhos': tabela5.xpath('td[@class="tbl-rc"]/span/text()').extract_first(),
                'partidas': tabela5.xpath('td[@class="tbl-matches-num"]/span/text()').extract_first()
            }
