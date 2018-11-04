# -*- coding: utf-8 -*-
import scrapy


class C1Spider(scrapy.Spider):
    name = 'c1'
    allowed_domains = ['fifa.com']
    start_urls = ['http://www.fifa.com/fifa-tournaments/statistics-and-records/worldcup/teams/index.html']

    def parse(self, response):
        for tabela1 in response.xpath('//table[@class="table tbl-t-most-victories"]/tbody/tr'):
            yield{
                'nome_selecao': tabela1.xpath('td[@class="tbl-teamname teamname-link"]/a/div/div[@class="t-n"]/span/text()').extract_first(),
                'titulos': tabela1.xpath('td[@class="tbl-edition-wins"]/span/text()').extract_first()
            }
        
