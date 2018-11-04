# -*- coding: utf-8 -*-
import scrapy


class ParticipacoesSpider(scrapy.Spider):
    name = 'participacoes'
    allowed_domains = ['fifa.com']
    start_urls = ['http://www.fifa.com/fifa-tournaments/statistics-and-records/worldcup/teams/index.html']

    def parse(self, response):
        for tabela3 in response.xpath('//table[@class="table tbl-t-most-trn"]/tbody/tr'):
            yield{
                'nome_selecao': tabela3.xpath('td[@class="tbl-teamname teamname-link"]/a/div/div[@class="t-n"]/span/text()').extract_first(), 
                'participacoes': tabela3.xpath('td[@class="tbl-participations"]/span/text()').extract_first() 
            }
