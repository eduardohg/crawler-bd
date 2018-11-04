# -*- coding: utf-8 -*-
import scrapy


class CartoesSpider(scrapy.Spider):
    name = 'cartoes'
    allowed_domains = ['fifa.com']
    start_urls = ['http://www.fifa.com/fifa-tournaments/statistics-and-records/worldcup/teams/index.html']

    def parse(self, response):
        for tabela5 in response.xpath('//table[@class="table tbl-t-most-cards"]/tbody/tr'):
            yield{
                'nome_selecao': tabela5.xpath('td[@class="tbl-teamname teamname-link"]/a/div/div[@class="t-n"]/span/text()').extract_first(),
                'totao_cartoes': tabela5.xpath('td[@class="tbl-cards-tot"]/span/text()').extract_first(),
                'cartoes_amarelos': tabela5.xpath('td[@class="tbl-y"]/span/text()').extract_first(),
                'cartoes_vermelhos': tabela5.xpath('td[@class="tbl-rc"]/span/text()').extract_first()
            }
