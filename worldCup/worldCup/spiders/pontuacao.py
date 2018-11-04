# -*- coding: utf-8 -*-
import scrapy


class PontuacaoSpider(scrapy.Spider):
    name = 'pontuacao'
    allowed_domains = ['fifa.com']
    start_urls = ['http://www.fifa.com/fifa-tournaments/statistics-and-records/worldcup/teams/index.html']

    def parse(self, response):
        for tabela2 in response.xpath('//table[@class="table tbl-alltimeranking"]/tbody/tr'):
            yield{
                'nome_selecao': tabela2.xpath('td[@class="tbl-teamname teamname-link"]/a/div/div[@class="t-n"]/span/text()').extract_first(),
                'pontuacao': tabela2.xpath('td[@class="tbl-points"]/span/text()').extract_first(),
                'partidas': tabela2.xpath('td[@class="tbl-matches-num"]/span/text()').extract_first(),
                'vitorias': tabela2.xpath('td[@class="tbl-win"]/span/text()').extract_first(),
                'empates': tabela2.xpath('td[@class="tbl-draw"]/span/text()').extract_first(),
                'derrotas': tabela2.xpath('td[@class="tbl-lost"]/span/text()').extract_first()
            }
